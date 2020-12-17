
public static class Modelankylosaurus extends ModelBase {
	private final ModelRenderer leftbackleg;
	private final ModelRenderer leftfrontleg;
	private final ModelRenderer rightbackleg;
	private final ModelRenderer rightfrontleg;
	private final ModelRenderer tail;
	private final ModelRenderer tail2;
	private final ModelRenderer tail3;
	private final ModelRenderer body;
	private final ModelRenderer head;

	public Modelankylosaurus() {
		textureWidth = 128;
		textureHeight = 128;

		leftbackleg = new ModelRenderer(this);
		leftbackleg.setRotationPoint(9.5F, 18.0F, 12.5F);
		leftbackleg.cubeList.add(new ModelBox(leftbackleg, 0, 51, -1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F, false));

		leftfrontleg = new ModelRenderer(this);
		leftfrontleg.setRotationPoint(9.5F, 18.0F, -13.5F);
		leftfrontleg.cubeList.add(new ModelBox(leftfrontleg, 59, 66, -1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F, false));

		rightbackleg = new ModelRenderer(this);
		rightbackleg.setRotationPoint(-9.5F, 18.0F, 12.5F);
		rightbackleg.cubeList.add(new ModelBox(rightbackleg, 13, 48, -1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F, false));

		rightfrontleg = new ModelRenderer(this);
		rightfrontleg.setRotationPoint(-9.5F, 18.0F, -13.5F);
		rightfrontleg.cubeList.add(new ModelBox(rightfrontleg, 0, 66, -1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 15.75F, 15.0F);
		tail.cubeList.add(new ModelBox(tail, 0, 36, -1.5F, -1.75F, 0.0F, 3, 3, 6, 0.0F, false));

		tail2 = new ModelRenderer(this);
		tail2.setRotationPoint(0.0F, 0.0F, 6.0F);
		tail.addChild(tail2);
		tail2.cubeList.add(new ModelBox(tail2, 12, 22, -1.5F, -1.75F, 0.0F, 3, 3, 6, 0.0F, false));

		tail3 = new ModelRenderer(this);
		tail3.setRotationPoint(0.5F, -0.75F, 6.0F);
		tail2.addChild(tail3);
		tail3.cubeList.add(new ModelBox(tail3, 0, 0, -4.5F, -1.5F, 6.0F, 8, 4, 5, 0.0F, false));
		tail3.cubeList.add(new ModelBox(tail3, 0, 19, -2.0F, -1.0F, 0.0F, 3, 3, 6, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 12.1667F, -0.5F);
		body.cubeList.add(new ModelBox(body, 0, 0, -13.0F, 0.8333F, -15.5F, 26, 5, 31, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 36, -11.0F, -2.1667F, -13.5F, 22, 3, 27, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 66, -9.0F, -3.1667F, -11.5F, 18, 1, 23, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(-0.5F, 15.5F, -16.0F);
		head.cubeList.add(new ModelBox(head, 0, 45, -2.5F, -1.5F, -3.0F, 5, 3, 3, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 9, -3.0F, -2.0F, -9.0F, 6, 4, 6, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		leftbackleg.render(f5);
		leftfrontleg.render(f5);
		rightbackleg.render(f5);
		rightfrontleg.render(f5);
		tail.render(f5);
		body.render(f5);
		head.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.leftfrontleg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.rightbackleg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.tail2.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
		this.tail3.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
		this.tail.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
		this.leftbackleg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.rightfrontleg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}